package com.example.socialapp.presentation.auth.login

import com.example.socialapp.presentation.graph.Destination

object LoginDestination : Destination {

    override val route: String
        get() = "login_destination"

    override val routeWithArgs: String
        get() = "login_with_args"
}