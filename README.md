
<div align="center">

<img src="docs/REXOR.Dark_gray.png" alt="REXOR Logo" width="280">

# REXOR
### Two Minds. One Better Result.

</div>



## How It Works

1. **Researcher Agent** — Investigates the user's request and produces an initial result.
2. **Reviewer Agent** — Evaluates the research, identifies weaknesses, assigns a score, and decides whether to approve or reject it.
3. **Refinement** — If rejected, the Researcher improves its work based on the reviewer's feedback.
4. **Final Result** — The system delivers the approved content or handles the configured review-round limit.

## Review Process

- Users can configure the maximum number of review rounds.
- The Reviewer assigns a score and an approval/rejection decision to each submission.
- The Researcher uses feedback to improve rejected submissions.

## Core Concept

Rather than relying on a single AI agent, REXOR uses two complementary perspectives to improve the quality of generated content.

**Research → Review → Refine → Result**

## Project Structure

```text
REXOR/
├── README.md
├── .gitignore
└── app/
    └── src/
        └── Main.java
```

## Architecture Overview

`Main` initializes and triggers `CollaborationManager`. `CollaborationManager` owns `ResearcherAgent` and `ReviewerAgent` (composition), both of which extend `Agent` (inheritance) and rely on `AIService` (association) to generate outputs.

```mermaid
classDiagram
    direction TB

    class Main {
        +main(String[] args)
    }

    class CollaborationManager {
        -ResearcherAgent researcher
        -ReviewerAgent reviewer
        +startProcess(String task, int maxRounds) String
    }

    class Agent {
        <<abstract>>
        #AIService service
        #String agentDuty
    }

    class ResearcherAgent {
        +research(String task, String feedback) String
    }

    class ReviewerAgent {
        +review(String task, String content) String
    }

    class AIService {
        -HttpClient client
        -String apiKey
        -String model
        +generateResponse(String prompt) String
    }

    Main --> CollaborationManager : starts workflow

    CollaborationManager *-- ResearcherAgent : owns
    CollaborationManager *-- ReviewerAgent : owns
    CollaborationManager ..> AIService : indirect via agents

    ResearcherAgent --|> Agent : extends
    ReviewerAgent --|> Agent : extends

    Agent --> AIService : uses
    ResearcherAgent --> AIService : inherits service
    ReviewerAgent --> AIService : inherits service
```

### Connection Flow
* **`Main` → `CollaborationManager`:** Entry point creates and runs the collaboration loop.
* **`CollaborationManager` *-- `ResearcherAgent` & `ReviewerAgent`:** Manager composes both worker agents to run research and review rounds.
* **`ResearcherAgent` & `ReviewerAgent` --|> `Agent`:** Both specialized agents inherit shared state (`service`, `agentDuty`) from the abstract `Agent` base class.
* **`Agent` → `AIService`:** Base class delegates model calls to `AIService`, which `ResearcherAgent` and `ReviewerAgent` use through inheritance.
## Tech Stack

- Java

## Project Status

Currently in development.
