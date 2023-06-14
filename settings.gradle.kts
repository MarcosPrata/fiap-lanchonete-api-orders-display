rootProject.name = "lanchonete"
include("domain")
include("application")

buildCache {
    local {
        directory = File(rootDir, "build-cache")
        removeUnusedEntriesAfterDays = 30
    }
}
