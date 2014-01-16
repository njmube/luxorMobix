package com.luxsoft.mobix

import org.springframework.security.access.annotation.Secured;

@Secured(['ROLE_ADMIN'])
class VentaDetController {
    static scaffold = true
}
