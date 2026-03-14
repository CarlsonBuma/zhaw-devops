# Task 2
Erstellung NPM-Projekt zeigen, Verwendung .gitignore, Dependencies aus Vorlesung: Einbau, Installation und Verwendung zeigen

## NPM
npm init
node index.js

## Dependency 1 - Chart
npm install chart.js@4.5.1

### Code

## Dependency 2 - AnimeJS
npm install animejs@3.2.2

### Code

## Dependency 3 - Datei‑Utilities
npm install fs-extra

### Code
    import fs from "fs-extra";

    async function demo() {
        await fs.writeFile("example.txt", "Hallo NPM!");
        const content = await fs.readFile("example.txt", "utf8");
        console.log("File content:", content);
    }

    demo();


## Dependency 4 - Leaflet.js

### Code

