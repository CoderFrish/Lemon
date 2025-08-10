import {sendMessage} from "./message.js";

const installer = (plugin) => {
    return {
        onLoaded() {},

        onEnabled() {
            sendMessage("Hello World!!")
        },

        onDisabled() {}
    }
}

export default {
    name: "TestPlugin",
    version: "1.0.0",
    installer
}
