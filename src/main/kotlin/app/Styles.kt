package app

import javafx.geometry.Pos.CENTER
import javafx.geometry.Pos.CENTER_LEFT
import javafx.scene.paint.Color
import javafx.scene.paint.Color.*
import javafx.scene.text.FontWeight.BOLD
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        // Containers
        val rowWrapper by cssclass()
        val contentWrapper by cssclass()
        val content by cssclass()
        val topbar by cssclass()
        val head by cssclass()
        val lightBackground by cssclass()
        val whiteBackground by cssclass()
        val defaultContentPadding by cssclass()
        val h1 by cssclass()
        val h2 by cssclass()

        // Dimensions
        val pageWidth = 980.px

        // Colors
        val lightBackgroundColor = c("#fafafa")
        val linkColor = c("#4078c0")
        val contrastColor = c("#d26911")
        val borderLineColor = c("#e5e5e5")
        val darkTextColor = c("#666")

        // Icons
        val logoIcon by cssclass()
        val repoIcon by cssclass()
        val codeIcon by cssclass()
        val issuesIcon by cssclass()
        val pullRequestsIcon by cssclass()
        val settingsIcon by cssclass()
    }

    init {
        val icon = mixin {
            minWidth = 16.px
            maxWidth = 16.px
            minHeight = 16.px
            maxHeight = 16.px
            backgroundColor += GRAY
        }

        rowWrapper {
            alignment = CENTER
        }

        rowWrapper child star {
            alignment = CENTER
        }

        contentWrapper {
            minWidth = pageWidth
            maxWidth = minWidth
            alignment = CENTER
        }

        content {
            minWidth = pageWidth
            maxWidth = minWidth
            alignment = CENTER_LEFT
        }

        defaultContentPadding {
            padding = box(15.px)
        }

        lightBackground {
            backgroundColor += lightBackgroundColor
        }

        whiteBackground {
            backgroundColor += Color.WHITE
        }

//        content child star {
//            prefWidth = pageWidth
//        }

        topbar {
            padding = box(10.px, 0.px)
            content {
                spacing = 20.px
            }
            borderColor += box(TRANSPARENT, TRANSPARENT, borderLineColor, TRANSPARENT)
            label {
                fontWeight = BOLD
                fontSize = 16.px
            }
        }

        h1 {
            spacing = 6.px
            padding = box(0.px, 0.px)
            label {
                textFill = linkColor
            }
        }

        h2 {
            spacing = 6.px
            alignment = CENTER_LEFT
            fontSize = 16.px
            label {
                textFill = darkTextColor
            }
        }

        head {
            padding = box(20.px, 0.px)
            spacing = 20.px
            backgroundColor += lightBackgroundColor
            label {
                fontWeight = BOLD
                fontSize = 18.px
            }
        }

        tabPane {
            prefWidth = pageWidth
            tabHeaderBackground {
                backgroundColor += lightBackgroundColor
                borderColor += box(TRANSPARENT, TRANSPARENT, borderLineColor, TRANSPARENT)
            }
            tab {
                backgroundColor += TRANSPARENT
                textFill = darkTextColor
                padding = box(7.px, 11.px)
            }
            tab and selected {
                backgroundColor += WHITE
                borderColor += box(contrastColor, TRANSPARENT, TRANSPARENT, TRANSPARENT)
                borderColor += box(TRANSPARENT, borderLineColor)
                borderColor += box(TRANSPARENT)
                borderWidth += box(3.px)
                borderWidth += box(1.px)
                borderWidth += box(1.px)
                borderRadius += box(3.px, 3.px, 0.px, 0.px)
                focusColor = TRANSPARENT
                faintFocusColor = TRANSPARENT
            }
        }

        // Icons
        repoIcon {
            shape = "M4 9H3V8h1v1zm0-3H3v1h1V6zm0-2H3v1h1V4zm0-2H3v1h1V2zm8-1v12c0 .55-.45 1-1 1H6v2l-1.5-1.5L3 16v-2H1c-.55 0-1-.45-1-1V1c0-.55.45-1 1-1h10c.55 0 1 .45 1 1zm-1 10H1v2h2v-1h3v1h5v-2zm0-10H2v9h9V1z"
            +icon
        }

        codeIcon {
            shape = "M9.5 3L8 4.5 11.5 8 8 11.5 9.5 13 14 8 9.5 3zm-5 0L0 8l4.5 5L6 11.5 2.5 8 6 4.5 4.5 3z"
            +icon
        }

        issuesIcon {
            shape = "M7 2.3c3.14 0 5.7 2.56 5.7 5.7s-2.56 5.7-5.7 5.7A5.71 5.71 0 0 1 1.3 8c0-3.14 2.56-5.7 5.7-5.7zM7 1C3.14 1 0 4.14 0 8s3.14 7 7 7 7-3.14 7-7-3.14-7-7-7zm1 3H6v5h2V4zm0 6H6v2h2v-2z"
            +icon
        }

        pullRequestsIcon {
            shape = "M11 11.28V5c-.03-.78-.34-1.47-.94-2.06C9.46 2.35 8.78 2.03 8 2H7V0L4 3l3 3V4h1c.27.02.48.11.69.31.21.2.3.42.31.69v6.28A1.993 1.993 0 0 0 10 15a1.993 1.993 0 0 0 1-3.72zm-1 2.92c-.66 0-1.2-.55-1.2-1.2 0-.65.55-1.2 1.2-1.2.65 0 1.2.55 1.2 1.2 0 .65-.55 1.2-1.2 1.2zM4 3c0-1.11-.89-2-2-2a1.993 1.993 0 0 0-1 3.72v6.56A1.993 1.993 0 0 0 2 15a1.993 1.993 0 0 0 1-3.72V4.72c.59-.34 1-.98 1-1.72zm-.8 10c0 .66-.55 1.2-1.2 1.2-.65 0-1.2-.55-1.2-1.2 0-.65.55-1.2 1.2-1.2.65 0 1.2.55 1.2 1.2zM2 4.2C1.34 4.2.8 3.65.8 3c0-.65.55-1.2 1.2-1.2.65 0 1.2.55 1.2 1.2 0 .65-.55 1.2-1.2 1.2z"
            +icon
        }

        settingsIcon {
            shape = "M14 8.77v-1.6l-1.94-.64-.45-1.09.88-1.84-1.13-1.13-1.81.91-1.09-.45-.69-1.92h-1.6l-.63 1.94-1.11.45-1.84-.88-1.13 1.13.91 1.81-.45 1.09L0 7.23v1.59l1.94.64.45 1.09-.88 1.84 1.13 1.13 1.81-.91 1.09.45.69 1.92h1.59l.63-1.94 1.11-.45 1.84.88 1.13-1.13-.92-1.81.47-1.09L14 8.75v.02zM7 11c-1.66 0-3-1.34-3-3s1.34-3 3-3 3 1.34 3 3-1.34 3-3 3z"
            +icon
        }

        logoIcon {
            minWidth = 28.px
            maxWidth = 28.px
            minHeight = 28.px
            maxHeight = 28.px
            backgroundColor += BLACK
            shape = "M8 0C3.58 0 0 3.58 0 8c0 3.54 2.29 6.53 5.47 7.59.4.07.55-.17.55-.38 0-.19-.01-.82-.01-1.49-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82.64-.18 1.32-.27 2-.27.68 0 1.36.09 2 .27 1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48 0 1.07-.01 1.93-.01 2.2 0 .21.15.46.55.38A8.013 8.013 0 0 0 16 8c0-4.42-3.58-8-8-8z"
        }

    }


}

fun main(args: Array<String>) {
    println(Styles().render())
}