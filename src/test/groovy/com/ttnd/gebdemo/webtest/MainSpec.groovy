package com.ttnd.gebdemo.webtest

import com.ttnd.gebdemo.pages.*
import geb.spock.GebSpec
import groovy.util.logging.Slf4j

@Slf4j
class MainSpec extends GebSpec {

    def setupSpec() {
        browser.driver.manage().window().maximize()
    }

    def "test that we can reach the home page"() {
    when:
        to HomePage
//        sleepSeconds(1)

    then:
        at HomePage
//        sleepSeconds(1)
    }

    def "test that we can reach the home page with parameters"() {
    when:
        to HomePage, param1: 1
//        sleepSeconds(1)

    then:
        at HomePage
//        sleepSeconds(1)
    }

    def "test that we have two links on home page"() {
    given:
        at HomePage
//        go "http://www.google.com"

    expect:
        links.size() == 2
//        sleepSeconds(1)
    }

    def "test that clicking on office listing page we reach the home listing page"() {
        /*TODO Uncomment setupSpec */
    given:
        at HomePage

    when:
        withNewWindow({
            homeListingLink.click()
//            sleepSeconds(5) /*TODO Remove after first demonstration */
        }, {
            at HomeListingPage
            log.info "At page $page"
        })
        log.info "At page $page"
    then:
        true
    }

    def "test that clicking on home listing page we reach the office listing page"() {
    setup:
        to HomePage
        waitFor { at HomePage }
//        sleepSeconds(5)

    when:
        officeListingLink.click()
//        sleepSeconds(5)
    then:
        at OfficeListingPage
    }

    def "test that we have 4 offices and 2 in delhi"() {
    given:
        at OfficeListingPage
//        sleepSeconds(1)

    expect:
        offices.size() == 4
        numberOfOfficesIn("noida") == 1
        numberOfOfficesIn("delhi") == 2
        numberOfOfficesIn("gurgaon") == 1
    }

    def "test that 3rd office is in gurgaon"() {
    given:
        at OfficeListingPage
//        sleepSeconds(1)

    expect:
        /*NOTE Don't write selectors in Spec files. This is for demonstration purposes only */
        $(text: iContains("Gurgaon"))*.tag() == ["html", "body", "ul", "li"]
        $(".office", 2).text().contains("Gurgaon")
    }

    def "test that office listing added after 5 seconds is handled"() {
    setup:
        to OfficeListingPage

    when:
        waitFor(6) { cityInput.displayed }; /* TODO Remove time from html after first demonstration */
        sleepSeconds(1)
        cityInput = "gurgaon"
        sleepSeconds(1)
    then:
        cityInput == "gurgaon"
    }

    def "test that home selection can be used"() {
    setup:
        to HomeListingPage

    when:
        sleepSeconds(2)
        home.setChecked("home1")
    then:
        home.getChecked() == "home1"
        sleepSeconds(2)
    }

    void sleepSeconds(int seconds) {
        log.debug "Sleeping for $seconds seconds"
        sleep(1000 * seconds)
    }
}
