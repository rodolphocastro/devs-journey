# The Dev's Journey

The Dev's journey is a project to apply gamification to the path of a Software Engineer.

## Getting Started

1. Fork this repository
2. Install Maven and Java SDK 21 (any flavor)
3. Install npm (or nvm) latest LTS version
4. Run `npm install` to install the dependencies and set-up `husky` hooks
5. Run `mvn dependency:resolve` to install the dependencies (note: if you prefer working offline use `mvn dependency:go-offline`)

## What's what in this repository?

### `.github/`

The `.github` folder contains the GitHub Actions workflows, issue templates and settings related to dependabot.

### `.husky/`

The `.husky` folder contains the hooks for the `husky` package, this is a tool to enable us to share `git hooks` easily between developers.

### `.` (aka: `root`)

The root folder contains the parent modules with common dependencies and settings to be applied to all over Maven modules as well as `docker-compose` files to run the dependencies locally.

### `dev-journey-core`

The `dev-journey-core` module contains the core APIs for the project, basically anything that is deemed "basic" from the point of view of users. This module homes REST APIs as well as Kafka connections.

### `dev-journey-common`

The `dev-journey-common` module contains the common definitions for the project such as the domain model, exceptions, and other common utilities.