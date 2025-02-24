# Minion User Guide

## Introduction
Minion is a simple task management application that helps you keep track of your tasks. You can add, list, mark, unmark, delete, and search tasks.

## Features

### 1. Add Tasks
You can add three types of tasks:
- **Todo**: A task without any date/time attached to it.
- **Deadline**: A task that needs to be done before a specific date/time.
- **Event**: A task that starts at a specific date/time and ends at a specific date/time.

#### Examples:
- Add a Todo: `todo read book`
- Add a Deadline: `deadline submit report /by end of the day`
- Add an Event: `event project meeting /from morning /to afternoon`

### 2. List Tasks
List all the tasks in your task list.

#### Example:
- `list`

### 3. Mark Tasks
Mark a task as done.

#### Example:
- `mark 1`

### 4. Unmark Tasks
Mark a task as not done.

#### Example:
- `unmark 1`

### 5. Delete Tasks
Delete a task from your task list.

#### Example:
- `delete 1`

### 6. Save and Load Tasks
Tasks are automatically saved to a file and loaded when the application starts.

### 7. Search Tasks
Search for tasks that contain a specific keyword.

#### Example:
- `find book`

## Command Summary
| Command | Format |
| ------- | ------ |
| Add Todo | `todo TASK_DESCRIPTION` |
| Add Deadline | `deadline TASK_DESCRIPTION /by DATE_OR_STRING` |
| Add Event | `event TASK_DESCRIPTION /from START_DATE_OR_STRING /to END_DATE_OR_STRING` |
| List Tasks | `list` |
| Mark Task | `mark INDEX` |
| Unmark Task | `unmark INDEX` |
| Delete Task | `delete INDEX` |
| Search Tasks | `find KEYWORD` |

## Examples
### Adding a Todo
```
todo read book
```

### Adding a Deadline
```
deadline submit report /by end of the day
```

### Adding an Event
```
event project meeting /from morning /to afternoon
```

### Listing Tasks
```
list
```

### Marking a Task
```
mark 1
```

### Unmarking a Task
```
unmark 1
```

### Deleting a Task
```
delete 1
```

### Searching for Tasks
```
find book
```