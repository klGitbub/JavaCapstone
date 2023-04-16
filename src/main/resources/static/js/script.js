// Add a publisher
const addPublisherForm = document.getElementById("add-publisher-form");
addPublisherForm.addEventListener("submit", (event) => {
    event.preventDefault();
    const publisherName=  document.getElementById("publisher-name")
    const publisherData = {
        name: publisherName.value
    };
    console.log(publisherData, "publisherData")
    axios.post("/publishers", publisherData)
        .then(response => {
            console.log(response.data);
            alert("Publisher added successfully");
            publisherName.value = "";
        })
        .catch(error => {
            console.error(error);
            alert("Failed to add publisher");
        });
});
// Get Publishers
function getPublisherLists(){
    const publisherList = document.getElementById("publisher-list");
    // Remove all child elements of the publisher list
    while (publisherList.firstChild) {
        publisherList.removeChild(publisherList.firstChild);
    }
    axios.get("/publishers")
        .then(response => {
            response.data.forEach(function(publisher) {
                // Create a new list item element for each user
                const listItem = document.createElement('li');

                // Set the inner text of the list item to the user's name
                listItem.innerText = publisher.name;

                // Add the list item to the publisher list
                publisherList.appendChild(listItem);
            });
        })
        .catch(error => {
            console.error(error);
            alert("Failed: Something went wrong while getting publishers");
        });
}

// Add Game Publisher
function addGamePublisherLists(){

    const publisherSelect = document.getElementById("game-publishers");
    console.log(publisherSelect, "publisher===>")
    // Clear the previous options
    publisherSelect.innerHTML = "";

    axios.get("/publishers")
        .then(response => {
            response.data.forEach(function(publisher) {
                const option = document.createElement('option');
                option.value = publisher.id.toString();
                option.text = publisher.name;
                publisherSelect.appendChild(option);
            });
        })
}

// Add Game Lists
function addGameLists(){

    const gameListElement = document.getElementById("game-list");
    while (gameListElement.firstChild) {
        gameListElement.removeChild(gameListElement.firstChild);
    }

    axios.get("/games")
        .then(response => {
            response.data.forEach(function(game) {
                // Create a new list item element for each game
                const listItem = document.createElement('li');

                // Set the inner text of the list item to the game's name
                listItem.innerHTML = `${game.game_name} <br /> Developer: ${game.game_developer} <br /> Genre: ${game.genre} <br/> Platform: ${game.game_platform} `;

                // Add the list item to the game list
                gameListElement.appendChild(listItem);
            });
        })
}

// Add Player Lists
function addPlayerLists(){

    const playerListElement = document.getElementById("player-list");
    while (playerListElement.firstChild) {
        playerListElement.removeChild(playerListElement.firstChild);
    }

    axios.get("/players")
        .then(response => {
            response.data.forEach(function(player) {
                // Create a new list item element for each player
                const listItem = document.createElement('li');

                // Set the inner text of the list item to the player's name
                listItem.innerHTML = `Name: ${player.first_name} ${player.last_name} <br /> Gender: ${player.gender} <br /> Age: ${player.age} `;

                // Add the list item to the game list
                playerListElement.appendChild(listItem);
            });
        })
}

// Add Players Games Lists
function addPlayerGameLists(){

    const playersList = document.getElementById("players-games-list");
    while (playersList.firstChild) {
        playersList.removeChild(playersList.firstChild);
    }

    axios.get("/players")
        .then(response => {
            const players = response.data;
            // Loop through each player
            players.forEach(player => {
                const playerItem = document.createElement("li");
                playerItem.innerHTML = `${player.first_name} ${player.last_name}`;

                const gamesList = document.createElement("ul");
                player.games.forEach(game => {
                    const gameItem = document.createElement("li");
                    gameItem.innerHTML = `${game.game_name} - ${game.genre}`;
                    gamesList.appendChild(gameItem);
                });

                playerItem.appendChild(gamesList);
                playersList.appendChild(playerItem);
            });
        })
}

// Add a game
const addGameForm = document.getElementById("add-game-form-field");
addGameForm.addEventListener("submit", (event) => {
    event.preventDefault();
    const formData = new FormData(addGameForm);
    const gameData = {
        game_name: formData.get("game_name"),
        publisher_id: formData.get("publisher_id"),
        game_developer: formData.get("game_developer"),
        genre: formData.get("genre"),
        game_platform: formData.get("game_platform")
    };
    axios.post("/games", gameData)
        .then(response => {
            console.log(response.data);
            alert("Game added successfully");
            addGameForm.reset();
        })
        .catch(error => {
            console.error(error);
            alert("Failed to add game");
        });
});
// Add a player
const addPlayerForm = document.getElementById("add-player-form-field");
addPlayerForm.addEventListener("submit", (event) => {
    event.preventDefault();
    const formData = new FormData(addPlayerForm);
    const playerData = {
        id: formData.get("player-id"),
        first_name: formData.get("player-first-name"),
        last_name: formData.get("player-last-name"),
        gender: formData.get("player-gender"),
        age: formData.get("player-age")
    };
    axios.post("/players", playerData)
        .then(response => {
            console.log(response.data);
            alert("Player added successfully");
            addPlayerForm.reset();
        })
        .catch(error => {
            console.error(error);
            alert("Failed to add player");
        });
});
// Add a player to a game
const addGameToPlayerForm = document.getElementById("add-player-to-game");
addGameToPlayerForm.addEventListener("submit", (event) => {
    event.preventDefault();
    const formData = new FormData(addGameToPlayerForm);
    const gameToPlayerData = {
        player_id: formData.get("player-id"),
        game_id: formData.get("game-id")
    };
    axios.post(`/players/${gameToPlayerData.player_id}/game/${gameToPlayerData.game_id}`)
        .then(response => {
            console.log(response.data);
            alert("Game added to player successfully");
            addGameToPlayerForm.reset();
        })
        .catch(error => {
            console.error(error);
            alert("Failed to add game to player");
        });
});


// main
(()=>{
    // Select all menu links
    const menuLinks = document.querySelectorAll('.menu a');
    const menuItems = document.querySelectorAll('.menu li');

    // add active class
    menuItems[0].classList.add('active');


// Select all content sections
    const contentSections = document.querySelectorAll('.section > div');

    // Loop through contentSections: Hide all except first one when page loads
    for (let i = 1; i < contentSections.length; i++) {
        // Set display to none
        contentSections[i].style.display = 'none';
    }

    // Add click event listener to each menu link
    menuLinks.forEach((link, index) => {


        link.addEventListener('click', (event) => {
            event.preventDefault(); // prevent default link behavior

            // remove active from all prev menus
            menuItems.forEach(menu => {
                menu.classList.remove('active');
            });

            // add active to current menu
            menuItems[index].classList.add('active');


            const targetSection = document.querySelector(link.hash);
            switch (targetSection.id){
                case "publishers-list":
                    getPublisherLists();
                    break;
                case "add-game-form":
                    addGamePublisherLists()
                    break;
                case "games-list":
                    addGameLists()
                    break;
                case "players-list":
                    addPlayerLists()
                    break;
                case "games-and-players-list":
                    addPlayerGameLists()
                    break;
                default:
                    break;

            }
            console.log(targetSection, "target section")

            // select corresponding content section
            if (targetSection) {
                // Hide all content sections
                contentSections.forEach((section) => {
                    section.style.display = 'none';
                });
                // Display target content section
                targetSection.style.display = 'block';
            }
        });
    });
})()

