package com.github.zimablue1995.everything.services

import com.intellij.openapi.project.Project
import com.github.zimablue1995.everything.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
