# LIBIS Login and Book Catalogue — Continuous Assessment 1 of 4

## How to build and run
1. Import as a Maven project in your IDE (IntelliJ / Eclipse).
2. Deploy the WAR to a Jakarta EE servlet container that supports the
   Jakarta namespace — **Tomcat 10 or newer** (Tomcat 9 and earlier use
   `javax.*` and will NOT work with this code as-is).
3. Visit `/login.jsp` (or just the app root — it's set as the welcome file).

Try logging in as, e.g.:
- email: `kagiso.molefe@example.com`
- password: `password1`

(All five seeded members follow the pattern `firstname.lastname@example.com` /
`passwordN`, N = 1–5, in the order they're seeded — see `DataInitListener`.)

## Design decisions (for the submission notes)
- **Member.java / Book.java** — used exactly as provided, no changes.
- **Data seeding & sharing** — a `@WebListener` (`DataInitListener`)
  populates the two `ArrayList`s once, at application startup, and stores
  them as **application-scoped attributes** (`ServletContext` attributes
  `"members"` and `"books"`). This was chosen over a shared static-holder
  class because it means both consumers read the exact same object through
  the mechanism each naturally already has access to:
  - `LoginServlet` → `getServletContext().getAttribute("members")`
  - `welcome.jsp` → plain EL, `${applicationScope.books}`
  No scriptlet or manual wiring is needed on the JSP side.
- **Login processing** — built as a `@WebServlet` (`LoginServlet`) rather
  than a processing JSP, since the brief allows either and a servlet keeps
  the branching logic out of a page.
- **Identity handoff** — on success, the matched `Member` is stored in the
  `HttpSession` and the servlet redirects (not forwards) to `welcome.jsp`,
  which avoids a form-resubmission warning if the user refreshes the
  catalogue page. On failure, the servlet forwards back to `login.jsp` with
  a request-scoped `error` attribute, so the message only appears for that
  one failed attempt.
- **welcome.jsp** — uses only JSTL (`c:if`, `c:choose`, `c:forEach`,
  `c:redirect`) and EL, no scriptlets. It also guards itself: if someone
  requests `welcome.jsp` directly without a session, it redirects back to
  `login.jsp`.
- **Book table** — shows title, author, genre, and availability for all 10
  books; the synopsis is intentionally omitted. Every row carries dummy
  Search / Borrow / Reserve links (`href="#"`).
