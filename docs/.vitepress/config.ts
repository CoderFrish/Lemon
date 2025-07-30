import { defineConfig } from 'vitepress'

export default defineConfig({
    base: '/LemonMint',
    titleTemplate: 'LemonMint | :title',
    themeConfig: {
        siteTitle: 'LemonMint',
        search: {
            provider: 'local'
        },
        sidebar: [
            {
                text: "概述",
                collapsed: false,
                items: [{ text: "LemonMint概述以及文件结构", link: "/" }],
            },
            {
                text: "misc",
                collapsed: false,
                items: [
                    { text: "warn_on_offine_mode", link: "misc/WarnOnOfflineMode" },
                    { text: "regionbar", link: "misc/RegionBar" },
                ]
            },
            {
                text: "optimisations",
                collapsed: false
            },
            {
                text: "experiment",
                collapsed: false,
                items: [
                    { text: "enable_datapack_function_command", link: "experiment/EnableDatapackFunctionCommand" },
                    { text: "enabled_folia_supported", link: "experiment/EnabledFoliaSupported" },
                    { text: "enabled_scoreboard", link: "experiment/EnabledScoreboard" },
                    { text: "fixed_teleport", link: "experiment/FixedTeleport" },
                    { text: "force_enable_command_block_execution", link: "experiment/ForceEnableCommandBlockExecution" },
                ]
            },
            {
                text: "fixes",
                collapsed: false
            },
        ],

        socialLinks: [
            { icon: "github", link: "https://github.com/MenthaMC/Lemint" },
        ],
    }
})
