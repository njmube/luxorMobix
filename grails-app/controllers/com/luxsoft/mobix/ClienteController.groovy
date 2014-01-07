package com.luxsoft.mobix

import org.springframework.security.access.annotation.Secured;

@Secured(['ROLE_ADMIN'])
class ClienteController {
    static scaffold = true
}
