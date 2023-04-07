// Add a publisher
const addPublisherForm = document.getElementById("add-publisher-form");
addPublisherForm.addEventListener("submit", (event) => {
    event.preventDefault();
    const formData = new FormData(addPublisherForm);
    const publisherData = {
        name: formData.get("publisher-name")
    };
    axios.post("/publishers", publisherData)
        .then(response => {
            console.log(response.data);
            alert("Publisher added successfully");
            addPublisherForm.reset();
        })
        .catch(error => {
            console.error(error);
            alert("Failed to add publisher");
        });
});
// Add a game
const addGameForm = document.getElementById("add-game-form");
addGameForm.addEventListener("submit", (event) => {
    event.preventDefault();
    const formData = new FormData(addGameForm);
    const gameData = {
        id: formData.get("game-id"),
        name: formData.get("game-name"),
        publisher: formData.get("game-publisher"),
        developer: formData.get("game-developer"),
        genre: formData.get("game-genre"),
        platform: formData.get("game-platform")
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
const addPlayerForm = document.getElementById("add-player-form");
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
// Add a game to a player
const addGameToPlayerForm = document.getElementById("add-game-to-player-form");
addGameToPlayerForm.addEventListener("submit", (event) => {
    event.preventDefault();
    const formData = new FormData(addGameToPlayerForm);
    const gameToPlayerData = {
        player_id: formData.get("player-id"),
        game_id: formData.get("game-id")
    };
    axios.post("/players/games", gameToPlayerData)
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

