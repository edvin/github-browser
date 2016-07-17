package app

import javafx.event.Event
import javafx.event.EventType

class IssueEvent(type: EventType<out Event>) : Event(type) {
    companion object {
        val ANY = EventType<IssueEvent>(Event.ANY, "ISSUE")
        val ISSUE_TAB_ACTIVATED = EventType(IssueEvent.ANY, "TAB_ACTIVATED")
    }
}
