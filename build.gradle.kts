plugins {
    kotlin("multiplatform") version "2.3.20"
}

repositories {
    mavenCentral()
}

kotlin {
    js {
        browser {
            commonWebpackConfig {
                outputFileName = "app.js"
            }
        }
        binaries.executable()
    }

    sourceSets {
        jsMain {
            dependencies {
                implementation(kotlinWrappers.react)
                implementation(kotlinWrappers.reactDom)
                implementation(kotlinWrappers.emotion.styled)
            }
        }
    }
}