package com.ttnd.gebdemo.pages

class OfficeListingPage extends HomePage {

    static url = "$BASE_URL/officeListing.html"

    static at = {
        driver.currentUrl == url &&
                title == 'Office Listing'
    }

    static content = {
        offices {
            $(".office")
        }
        numberOfOfficesIn { String city ->
            offices.filter(text: iContains(city))
                    .size()
        }
        cityInput {
            $("#city")
        }
    }
}
