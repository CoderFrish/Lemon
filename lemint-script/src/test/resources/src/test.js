const installer = {
    onLoaded() {
        plugin.config.loadDefault()
    },

    onEnabled() {
    },

    onDisabled() {
    }
}

export default {
    name: "Test",
    version: "1.0.0",
    installer,
    load: "WORLD_POST",
    authors: [
        {
            name: "MenthaMC",
            email: "menthamc@menthamc.com",
            website: "https://github.com/MenthaMC"
        }
    ],
    website: {
        issues: "https://github.com/MenthaMC/Script-Example/issues",
        home: "https://github.com/MenthaMC",
        source: "https://github.com/MenthaMC/Script-Example"
    }
}
