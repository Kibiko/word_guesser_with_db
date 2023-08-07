# Word Guesser

Demo Spring app for BNTA. Needs a local postgres db called `word_guesser`. Routes are:

- `GET /players` - get all players

- `GET /players/{id}` - get player by id

- `POST /players` - create new player

```json
<!-- RequestBody format: -->

{
	"name": "playerName"
}
```

- `GET /games` - get all games

- `GET /games/{id}` - get game by id

- `POST /games?playerId=1` - create new game for given player

- `POST /games/{id}` - make guess in given game

```json
<!-- RequestBody format: -->

{
	"letter": "a"
}
```

- `GET /games/guessed` - get all letters guessed in current game