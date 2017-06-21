# Contributing

Thank you for considering contributing to Pseudoloc! Contributions are welcome and encouraged!
 
The below guidelines explain how we would like to engage with the community. Before you begin, we encourage you to be familiar with the concept of [pseudo-localization](https://en.wikipedia.org/wiki/Pseudolocalization), and read our [README](README.md) in full.

## Programming Languages and Platforms

The pseudoloc-core, pseudoloc-adapter-*, and pseudoloc-cli are written in [Kotlin](https://kotlinlang.org/), and run on the JVM.

Please use either Kotlin or Java for contributions to any of the above listed modules.

Virtually every software platform has its own way of handling translations, and we want to support as many of them as possible, including non-JVM environments.

## New Features

If you would like to contribute a new feature to Pseudoloc, we ask that you start by creating a new issue with the tag "proposal", and describe what you have in mind. We would like to have a dialog and determine if it will be a good fit for the project. 

We anticipate new features will mostly fall into these categories.

### Adapters

The upper bound on the number of Pseudoloc Adapter modules is the number of platforms with different language string formats. We strongly encourage contributions in this area, whether they may be new Adapters targeting new platforms, or enhancements/maintenance on existing Adapters.

#### New Adapters

If you're creating a new Adapter, please:
- Follow the Maven module structure already laid out (folder names, artifact names, etc)
- Include tests
  - At least one integration test
  - Unit tests are nice to have. The more coverage, the merrier, within reason.
- Add a README
  - Indicate what platform it supports. It should also be in the module folder and artifact names, but this redundancy will add some context to your fellow users' screens
  - A brief demonstration how to use it (where's the entry point? etc)
  - Include a link to any relevant specifications or documentation for the platform's language string handling
  - List any third party Pseudoloc UIs making use of it
- Add support for your Adapter to the CLI

#### Modifying Adapters

If you're modifying an existing Adapter, please:
- Be proactive and reach out to any third-party Pseudoloc UI projects listed as using it on the module's README
- Make sure the CLI still works with it

### Core

Changes to any code in pseudoloc-core need to be carefully considered, tested thoroughly, and any breaking changes accounted for and documented.

### UIs

There are lots of possibilities for clever new UIs.
- IDE plugins
- Build tool plugins
- Web services

Due to the anticipated burden of maintaining various UIs, we ask that you create your own repository for your UI rather than fork/pull request it to Pseudoloc. That will allow us to remain focused on the Core and Adapters, and allow you more freedom to implement your UI however you see fit.

Once your Pseudoloc UI project is ready to share, we would love to hear! Please create an issue with:
- Tag: ui
- A link to your project
- Pseudoloc Adapters you depend on
