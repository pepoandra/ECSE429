# Testing Guidelines

## Acceptance Testing

Testing would include the CRUD (Create, Read, Update, Delete) approach in addition to the following list of techniques.

1. Error Guessing
- Equivalence Partitioning (Weak or Strong)
- Boundary Value Analysis (Standalone, Robustness or Worst Case)
- Category Partitioning
- Decision Table
- Cause-Effect Modeling

## Supplementary Considerations

Apache Commons Collections implement interfaces of abstract data structures. To simply testing, only operations defined for a data structure would be considered. For example, a *queue* supports insertion, removal and examination. Thus, the testing would at least cover these operations.

## Testing Tools

`AssertJ` would be used as a testing framework, `Apache Ant` as a build system and `Jenkins` as a continuous integration framework. The testing procedures would be automated.

## Code Style
All code must be formatted with Google's Java Style for consistency.
