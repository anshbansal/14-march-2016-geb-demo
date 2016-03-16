package com.ttnd.gebdemo.pages

class HomePage extends BasePage {

    static url = "$BASE_URL/home.html"

    static at = {
        title == "Home Page"
    }

    static content = {
        links {
            $(".links")
        }
        officeListingLink {
            $("#office-listing-link")
        }
        homeListingLink {
            $("#home-listing-link")
        }
    }
}
