package com.ttnd.gebdemo.pages

import geb.module.RadioButtons

class HomeListingPage extends BasePage {

    static url = "$BASE_URL/homeListing.html"

    static at = {
        driver.currentUrl == url &&
                title == 'Home Listing'
    }

    static content = {
        home {
            $(name: 'home').module(RadioButtons)
        }
    }
}
