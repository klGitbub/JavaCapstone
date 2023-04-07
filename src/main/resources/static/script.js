function addPublisher() {
    const publisherName = document.getElementById("publisher-name").value;
    const data = { name: publisherName };

    fetch("/publishers",  {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(publisher => {
            document.getElementById("publisher-name").value = "";

            const table = document.getElementById('publishers-table');
            const row = table.insertRow(-1);
            const idCell = row.insertCell(0);
            const nameCell = row.insertCell(1);
            idCell.textContent = publisher.id;
            nameCell.textContent = publisher.name;
        })
        .catch(error => console.error(error));

}

function showPublishers() {
    fetch("/publishers")
        .then(response => response.json())
        .then(publishers => {
            const table = document.getElementById("publishers-table")
            while (table.rows.length > 1) {
                table.deleteRow(1);
            }

            for (const publisher of publishers) {
                const row = table.insertRow(-1);
                const idCell = row.insertCell(0);
                const nameCell = row.insertCell(1);
                idCell.textContent = publisher.id;
                nameCell.textContent = publisher.name;
            }

        })
        .catch(error => console.error(error));
}
function addGame() {
    const gameName = document.getElementById("game-name").value;
    const gameDeveloper = document.getElementById("game-publisher").value;
    const gamePublisher = document.getElementById("game-developer").value;
    const gameGenre = document.getElementById("game-genre").value;
    const gamePlatform = document.getElementById("game-platform").value;

    fetch('/publishers?name=${gamePublisher}')
        .then(response => response.json())
        .then(publishers => {
            if (publishers.length === 0) {
                console.error(`Publisher '${gamePublisher} does not exist`);
                return;
            }
            const publisherId = publishers[0].id;

            const data = { name: gameName,
                developer: gamePublisher,
                publisher: gameDeveloper,
                genre: gameGenre,
                platform: gamePlatform,
                publisherId: publisherId
            };
            fetch("/games",  {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(data)
            })
                .then(response => response.json())
                .then(game => {
                    document.getElementById("game-name").value = "";
                    document.getElementById("game-publisher").value = "";
                    document.getElementById("game-developer").value = "";
                    document.getElementById("game-genre").value = "";
                    document.getElementById("game-platform").value = "";

                    const table = document.getElementById('games-table');
                    const row = table.insertRow(-1);
                    const idCell = row.insertCell(0);
                    const nameCell = row.insertCell(1);
                    const publisherCell = row.insertCell(2);
                    const developerCell = row.insertCell(3);
                    const genreCell = row.insertCell(4);
                    const platformCell = row.insertCell(5);
                    idCell.textContent = game.id;
                    nameCell.textContent = game.name;
                    publisherCell.textContent = game.publisher;
                    developerCell.textContent = game.developer;
                    genreCell.textContent = game.genre;
                    platformCell.textContent = game.platform;
                })
                .catch(error => console.error(error));
        })
        .catch(error => console.error(error));



}

function showGames() {
    fetch("/games")
        .then(response => response.json())
        .then(games => {
            const table = document.getElementById("games-table")
            while (table.rows.length > 1) {
                table.deleteRow(1);
            }

            for (const game of games) {
                const row = table.insertRow(-1);
                const idCell = row.insertCell(0);
                const nameCell = row.insertCell(1);
                const developerCell = row.insertCell(2);
                const publisherCell = row.insertCell(3);
                const genreCell = row.insertCell(4);
                const platformCell = row.insertCell(5);
                idCell.textContent = game.id;
                nameCell.textContent = game.name;
                developerCell.textContent = game.developer;
                publisherCell.textContent = game.publisher;
                genreCell.textContent = game.genre;
                platformCell.textContent = game.platform;

            }

        })
        .catch(error => console.error(error));
}
function addPlayer() {
    const firstName = document.getElementById("first-name").value;
    const lastName = document.getElementById("last-name").value;
    const gender = document.getElementById("gender").value;
    const age = document.getElementById("age").value;
    const data = {
        firstName: firstName,
        lastName: lastName,
        gender: gender,
        age: age
    };

    fetch("/players",  {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(player => {
            document.getElementById("first-name").value = "";
            document.getElementById("last-name").value = "";
            document.getElementById("gender").value = "";
            document.getElementById("age").value = "";

            const table = document.getElementById('players-table');
            const row = table.insertRow(-1);
            const idCell = row.insertCell(0);
            const firstNameCell = row.insertCell(1);
            const lastNameCell = row.insertCell(2);
            const genderCell = row.insertCell(3);
            const ageCell = row.insertCell(4);
            idCell.textContent = player.id;
            firstNameCell.textContent = player.firstName;
            lastNameCell.textContent = player.lastName;
            genderCell.textContent = player.gender;
            ageCell.textContent = player.age;
        })
        .catch(error => console.error(error));

}

function showPlayers() {
    fetch("/players")
        .then(response => response.json())
        .then(publishers => {
            const table = document.getElementById("players-table")
            while (table.rows.length > 1) {
                table.deleteRow(1);
            }

            for (const player of players) {
                const row = table.insertRow(-1);
                const idCell = row.insertCell(0);
                const firstNameCell = row.insertCell(1);
                const lastNameCell = row.insertCell(2);
                const genderCell = row.insertCell(3);
                const ageCell = row.insertCell(4);
                idCell.textContent = player.id;
                firstNameCell.textContent = player.firstName;
                lastNameCell.textContent = player.lastName;
                genderCell.textContent = player.gender;
                ageCell.textContent = player.age;
            }

        })
        .catch(error => console.error(error));
}
function addGameToPlayer() {
    const gameId = document.getElementById("game-id").value;
    const playerId = document.getElementById("player-id").value;

    fetch(`/games/${gameId}`)
        .then(response => response.json())
        .then(game => {
            if (!game) {
                console.error(`Game with ID '${gameId}' does not exist`);
                return;
            }

            fetch(`/players/${playerId}`)
                .then(response => response.json())
                .then(player => {
                    if (!player) {
                        console.error(`Player with ID '${playerId}' does not exist`);
                        return;
                    }

                    const data = {
                        gameId: gameId,
                        playerId: playerId
                    };
                    fetch("/player-games", {
                        method: "POST",
                        headers: { "Content-Type": "application/json" },
                        body: JSON.stringify(data)
                    })
                        .then(response => response.json())
                        .then(playerGame => {
                            document.getElementById("game-id").value = "";
                            document.getElementById("player-id").value = "";

                            const table = document.getElementById("player-games-table");
                            const row = table.insertRow(-1);
                            const idCell = row.insertCell(0);
                            const gameCell = row.insertCell(1);
                            const genreCell = row.insertCell(2);
                            idCell.textContent = game.id;
                            gameCell.textContent = game.name;
                            genreCell.textContent = game.genre;
                        })
                        .catch(error => console.error(error));
                })
                .catch(error => console.error(error));
        })
        .catch(error => console.error(error));
}

function showPlayerGames(playerId) {
    fetch(`/player-games/${playerId}`)
        .then(response => response.json())
        .then(games => {
            const table = document.getElementById("player-games-table");
            while (table.rows.length > 1) {
                table.deleteRow(1);
            }

            for (const game of games) {
                const row = table.insertRow(-1);
                const idCell = row.insertCell(0);
                const nameCell = row.insertCell(1);
                const genreCell = row.insertCell(2);
                idCell.textContent = game.id;
                nameCell.textContent = game.name;
                genreCell.textContent = game.genre;
            }
        })
        .catch(error => console.error(error));
}
document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("add-publisher-form").addEventListener("submit", event => {
        event.preventDefault();
        addPublisher();
    });
    document.getElementById("publishers-link").addEventListener("click", event => {
        event.preventDefault();
        showPublishers();
    });
    document.getElementById("add-game-form").addEventListener("submit", event => {
        event.preventDefault();
        addGame();
    });
    document.getElementById("games-link").addEventListener("click", event => {
        event.preventDefault();
        showGames();
    });
    document.getElementById("add-player-form").addEventListener("submit", event => {
        event.preventDefault();
        addGame();
    });
    document.getElementById("players-link").addEventListener("click", event => {
        event.preventDefault();
        showGames();
    });
    document.getElementById("add-game-to-player-form").addEventListener("submit", event => {
        event.preventDefault();
        addGameToPlayer();
    });
    document.getElementById("games-players-link").addEventListener("click", event => {
        event.preventDefault();
        showPlayerGames();
    });
});



