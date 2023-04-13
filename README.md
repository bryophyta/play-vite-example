# play-vite-example

A WIP example of integrating Vite into a Play app for easy development and deployment

- `sbt run` to run Play and Vite in dev mode (with HMR for Vite)
- All sbt-native-packager outputs automatically include built vite assets (`sbt dist`, `sbt Debian/packageBin`, etc.etc.)

You are (currently) responsible for running `npm install` in the client directory!
