# RiskGame

This repo implements the [Risk Board Game](https://en.wikipedia.org/wiki/Risk_%28game%29).  It has GUI for interacting with the user. This was implemented as a part of our college assignment.

It also contains a AI bot which can be used to play with the user. NOTE: The AI is not attached to the RiskGame app.

## Requirements

To run the game you need
1. Java JDK 1.8+
2. Gradle 6.6.1

### Build

```bash
gradle assemble
```

### Tests

The test will play with one another until the end. (It takes a lot of time, avoid it!)

```bash
gradle build
```

### Run

#### Run the game

```bash
gradle run
# or
gradle riskgame:run
```

#### Run the bot

```bash
gradle riskgamebot:run
```
