
<div align="center">

<img src="docs/REXOR_gray.png" alt="REXOR Logo" width="280">

# REXOR
### Two Minds. One Better Result.

</div>

# REXOR
### Two Minds. One Better Result.

REXOR is a dual-agent AI system where two specialized agents collaborate to research, critique, and refine content through an iterative feedback loop.

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

## Tech Stack

- Java

## Project Status

Currently in development.
