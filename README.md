# Message Router

![Java CI](https://github.com/YOUR-USERNAME/message-router/actions/workflows/maven.yml/badge.svg)

A small Java project that routes customer messages to the right team
(cards, transfers, loans, or general support). It is built test-first (TDD)
and designed so the classifier can be swapped, for example for an AI model.

## How it works

```
Message -> MessageClassifier -> Category -> MessageRouter -> team email
```

| Class | Role |
|---|---|
| `Category` | Enum of teams, each with its email |
| `MessageClassifier` | Interface: decides the category of a message |
| `KeywordClassifier` | Implementation using keywords (no API needed) |
| `MessageRouter` | Validates input and returns the team email |
| `Main` | Command-line demo |
| `RouteController` | `POST /api/route` endpoint |
| `AppConfig` | Wires the classifier and router (dependency injection) |
| `ApiExceptionHandler` | Turns validation errors into HTTP 400 |

## Design ideas used

- **Dependency injection**: `MessageRouter` receives a `MessageClassifier`.
- **Strategy pattern**: swap `KeywordClassifier` for any other classifier.
- **Enum with data**: each `Category` carries its team email.
- **Fail fast**: null and blank messages are rejected with clear messages.
- **TDD**: tests use a lambda fake, so no external service is needed.

## Web API

Start the server:

```
mvn spring-boot:run
```

Open http://localhost:8080 for the form, or call the API:

```
curl -X POST http://localhost:8080/api/route \
  -H "Content-Type: application/json" \
  -d '{"message":"My card was declined"}'
```

Response:

```
{"category":"CARDS","team":"cards-team@bank.example"}
```

Invalid input returns HTTP 400, for example `{"error":"empty message"}`.
The demo shows the destination only. It does not send any email.

## Run the tests

```
mvn test
```

## Run the demo

```
mvn package
java -cp target/classes com.example.router.Main
```

Example:

```
Type a customer message (empty line to quit):
My card was declined
  -> cards-team@bank.example
```

## Roadmap

- [ ] `ClaudeMessageClassifier` using the Anthropic API (needs an API key)
- [ ] Return a confidence score and a short summary
- [ ] Simple web front end

## Requirements

Java 17 or newer, Maven 3.
