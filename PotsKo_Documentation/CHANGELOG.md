# 📦 Changelog

## [May 13, 2025]

### ✨ Added
- Initial draft design of the **PlantPal logo** using concept sketches focused on plant and nature themes.

### ✅ Changed
- Finalized the **System Flow Diagram** using Draw.io
  - Clearly mapped user interaction flow
  - Included fallback behaviors for missing data or internet issues
  - Integrated AI assistant and weather advisory flow

- Finalized the **UML Diagram** with OOP concepts
  - Included classes like `User`, `Dashboard`, `PlantData`, `ReminderSystem`, and `WeatherAssistant`
  - Added associations (has-a, uses, composed of)
  - Annotated with helpful non-technical descriptions for documentation clarity

## [May 15, 2025]

### ✨ Added
- **Project folder structure** based on Maven standard:
  - Included folders: `model/`, `view/`, `controller/`, `data/`, `utils/`
  - Added placeholder class files to match planned components (`Plant`, `User`, `Reminder`, etc.)
- Created initial **JSON data plan** for static plant information:
  - Watering frequency, sunlight needs, plant category, temperature range
- Began **Figma UI Design**:
  - Designed minimalist layout for main dashboard, plant list, and journal screen
  - Ensured compatibility with Java Swing's layout limitations

### ✅ Changed
- **Updated project timeline** to accommodate **Database Integration**:
  - Week 2: Setup SQLite DB structure (tables for users, reminders, journals)
  - Week 3: Implement read/write between database and application logic
  - Week 4: Test interactions between JSON (static data) and database (dynamic data)
  - Clearly separated responsibilities: static data (JSON), dynamic user data (Database)
- Improved overall data structure plan:
  - Plant JSON file will be used only for read-only plant references
  - User inputs (selected plants, reminders, journals) will be stored in the database

### 🧠 Discussed
- Clarified the role of the **controller** package in MVC:
  - Handles logic between model and view (e.g., user events, data validation)
- Explained where to store the JSON data file (in `/resources` or `/data` folder)
- Strategized GitHub commits:
  - Agreed to commit project structure early to enable version tracking even before coding starts

## [May 17, 2025]

### ✨ Added
- Finalized **Dashboard UI Mockup** in Figma:
  - Included *Recently Added Plants* and *Recently Watered* sections for dynamic display
  - Added sorting filter under **Category**
  - Introduced concept of **Growth Progress Bar** per plant for visual feedback on care

- Designed initial layout for **Weather Assistant Widget** on the dashboard:
  - Placeholder space for location-based temperature and plant care advisory
  - Allocated design space for assistant name and personality preview

### ✅ Changed
- Improved organization of Figma layers and frame naming conventions
- Tweaked spacing and icon placement for better alignment and consistency

### 🧠 Discussed
- Explored feasibility of dynamic homepage features:
  - Confirmed recent plant additions and watering updates are **doable** in Java Swing with database tracking
- Reviewed idea of using icons from **Flaticon**:
  - Confirmed that attribution is required even in academic projects
  - Chose to add attributions inside `README.md` for maintainability

## [May 18, 2025]

### ✨ Added
- Completed **Journal Page** UI in Figma:
  - Final title selected: **"Yui’s Garden Journal"**
  - Entry card system with timestamp, note field, and image support
  - Planned interaction for adding new entries and editing past logs

- Brainstormed names for AI features:
  - Final Weather Assistant name: `PanahAI`
  - Final Plant AI Assistant name: `SibolAI`

### ✅ Changed
- Confirmed that **Growth Progress** can be initialized from user input or default "Day 0"
- Updated README attribution strategy for icons:
  - Will credit individual creators with links
  - Attribution to be added before project submission or publication

### 🧠 Discussed
- Reassured scope of features (growth tracker, dashboard sorting) are doable within the 5-week timeline
- Clarified use of Java Swing’s **JProgressBar** for visual growth tracker

## 📦 Weekly Changelog (May 19 – May 24, 2025)

### ✨ Added

- ✅ **Installed Chocolatey & SQLite for local development**
  - Chocolatey installed via PowerShell script
  - SQLite v3.49.2 installed using `choco install sqlite`
  - Verified installation using `sqlite3 --version`

- ✅ **Set up local database environment**
  - Confirmed SQLite will be the local backend database
  - Database file (`potsko.db`) will be created inside `src/main/java/controller/db/`
  - Established clear separation:
    - `plants.json` → Static read-only plant data
    - SQLite DB → Dynamic data (user, reminders, journal)

- ✅ **Updated project structure**
  - Added `db/` folder inside `controller/` to handle SQLite interactions
  - Finalized MVC interactions

- ✅ **Database planning**
  - Tables to be implemented:
    - `users` (for login/signup)
    - `reminders`
    - `journal`
    - `user_plants`
  - Discussed login and signup handling logic via SQLite (`users` table)

### ✅ Changed

- Reorganized folder structure to better separate static (`json`) vs dynamic (`db`) data
- Confirmed SQLite will be accessed directly from Java through JDBC
- Adjusted workflow to work **within the project folder**, not outside

### 🧠 Discussed

- SQLite is sufficient as a backend for local desktop applications like PotsKo
- No external backend (e.g. Node, Django) is necessary unless cloud sync is introduced
- Login/signup flows will be written in Java, with input validation and encrypted password storage
- Development will continue inside VS Code, integrated into the existing Java project structure

## 📦 Weekly Changelog (May 26 – May 31, 2025)

### ✨ Added

- Integrated CardLayout for page navigation in `MainFrame.java`, enabling seamless switching between the landing, login, and signup panels.
- Created `LoginPanel.java` and `SignupPanel.java` in the `view/` directory.
    - Designed forms with username and password fields.
    - Added navigation buttons to switch between panels.
    - Set up button actions and layout (database logic in progress as of June 1).
- Outlined plans for database authentication via a `UserDAO` (to be placed in `db/` or `model/`).
    - Discussed using JDBC for SQLite integration.
    - Noted intent to use hashed passwords in production.

### ✅ Enhanced Project Structure

- Consolidated navigation logic within `MainFrame.java` for maintainability.
- Passed `MainFrame` references to panels, enabling UI-driven page switching.
- Separated UI logic (in panels) from future database logic (in DAO classes).
- Provided sample templates and code examples for panel and navigation setup.

### 🧠 Discussed

- Best practices for Java Swing navigation: using CardLayout in a central frame.
- Decoupling UI layout from navigation logic by referencing `MainFrame` in panels.
- Responsibilities and structure of `LandingPage`, `LoginPanel`, and `SignupPanel`.
- Planned integration of authentication and registration with the database.
- Noted that login and signup/database connection work is ongoing as of June 1.


## 📦 Weekly Changelog (June 2 – June 8, 2025)

### ✨ Added

- Created and integrated an SQLite database for persistent data storage, including `users`, `plants`, and `growthstages` tables, with proper primary and foreign key constraints.
- Implemented auto-timestamping for user registration (`date_created DATETIME DEFAULT CURRENT_TIMESTAMP`) in the `users` table.
- Added project documentation and step-by-step guides for using DB Browser for SQLite: creating tables, setting default values, and establishing foreign key relationships.
- Built initial database connection logic in the Java backend, enabling user registration data to be saved and retrieved.
- Completed initial UI for `LoginPanel` and `SignupPanel` using custom fonts (`FontUtils`) and consistent button styling (`ButtonUtils`).
- Designed and integrated reusable green button styles in `ButtonUtils` for a cohesive look across authentication panels.
- Began constructing the homepage/dashboard UI based on the Figma mockup, including navigation sidebar, plant dashboard cards, growth progress, reminders, and placeholders for AI/weather panels.
- Exported and prepared Figma assets (icons, backgrounds) for use in the Java Swing UI.

### ✅ Enhanced Project Structure

- Refined SQL table schemas for data integrity (e.g., `username` and `email` as UNIQUE in `users`, explicit foreign key relationships in `growthstages`).
- Standardized use of explicit, best-practice SQL syntax for foreign keys (`FOREIGN KEY (plant_id) REFERENCES plants(plant_id)`).
- Unified and improved onboarding documentation for contributors regarding database setup, file management, and the difference between local and online SQLite tools.
- Clarified and standardized panel navigation for easier scaling and seamless switching between views.
- Improved layout handling with `BoxLayout` and manual positioning for pixel-perfect UI alignment.
- Documented utility class usage (`FontUtils`, `ButtonUtils`) for consistent styling and easier maintenance.

### 🧠 Discussed

- Best practices for defining default values and foreign keys in SQLite and dealing with quirks in GUI tools versus raw SQL.
- Strategies for editing tables and migrating schema changes safely in SQLite, including backup and migration patterns.
- Approaches for integrating external APIs (OpenWeatherMap for weather, OpenAI for plant Q&A) and managing API keys securely.
- Prioritizing core features (authentication, navigation, dashboard basics) and using placeholders/static data for demos.
- Set clear next steps: finalize homepage UI, implement weather/AI API calls, and polish navigation between all panels.
