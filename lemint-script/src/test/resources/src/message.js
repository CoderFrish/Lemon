const Bukkit = Java.type("org.bukkit.Bukkit")

export const sendMessage = (message) => {
    Bukkit.getConsoleSender().sendMessage(message)
}
