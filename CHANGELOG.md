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
- Decided to include only necessary, meaningful features to avoid burnout
