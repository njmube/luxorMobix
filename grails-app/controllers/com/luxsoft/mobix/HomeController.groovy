package com.luxsoft.mobix

import org.springframework.security.access.annotation.Secured;

@Secured(['ROLE_USER'])
class HomeController {

    def index() { }
}
