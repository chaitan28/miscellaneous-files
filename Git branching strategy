# GIT BRANCHING STRATEGY

1. **Development Branch**

   * Feature branches are merged into `development`, triggering deployment to the **dev server** for initial testing.
   * Developers validate functionality and catch early issues.

2. **Release Branch**

   * After thorough testing in development, the code is promoted to the `release` branch.
   * This version is deployed to the **staging server** for further testing, QA validation, and possibly user acceptance testing (UAT).

3. **Master Branch (Production)**

   * Once the release branch proves stable in staging, it is merged into `master`, deploying to **production**.
   * This ensures only **fully tested** code reaches end users.

---

### 🔧 **Bugfix Branch**

* **Use when:** Fixing bugs found in the `development` or `release` branch before production.
* **Branch from:** `development` or `release` (depending on where the bug is found).
* **Merge into:** `development` (and `release` if the fix is urgent or part of the upcoming release).
* **Purpose:** Allows teams to fix non-critical bugs without disrupting other feature work.

### 🔥 **Hotfix Branch**

* **Use when:** Fixing critical bugs directly in **production**.
* **Branch from:** `master`
* **Merge into:** `master`, `development`, and `release` (to ensure the fix is applied everywhere).
* **Triggers:** Immediate deployment to production.
* **Purpose:** Fast emergency fixes without waiting for the next planned release.

---

### 🧠 Example Workflow Summary (with Hotfix & Bugfix)

1. `feature/*` → merged into `development` → deployed to **dev**
2. `bugfix/*` → merged into `development` (or `release`) → deployed to **dev** or **staging**
3. `release/*` → merged into `master` → deployed to **production**
4. `hotfix/*` → branched from `master`, merged back into `master`, `release`, and `development` → deployed to **production**

---

Let me know if you’d like a visual diagram or Git commands to go with this.

