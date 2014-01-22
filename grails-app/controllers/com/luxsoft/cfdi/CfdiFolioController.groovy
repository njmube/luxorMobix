package com.luxsoft.cfdi

import org.springframework.security.access.annotation.Secured;

@Secured(['ROLE_ADMIN'])
class CfdiFolioController {
    static scaffold = true
}
