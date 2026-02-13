# Quick Setup Guide - Lombok + IntelliJ IDEA

## ✅ Step-by-Step Setup

### 1. Install Lombok Plugin in IntelliJ

**Method 1: From Settings**
1. `File` → `Settings` (or `Ctrl+Alt+S`)
2. `Plugins` → `Marketplace`
3. Search for **"Lombok"**
4. Click `Install`
5. Restart IntelliJ IDEA

**Method 2: Auto-Install**
- IntelliJ should prompt you automatically when opening the project
- Click "Install Plugin" when prompted

---

### 2. Enable Annotation Processing

**CRITICAL STEP - Must Do This!**

1. `File` → `Settings` → `Build, Execution, Deployment` → `Compiler` → `Annotation Processors`
2. ✅ Check **"Enable annotation processing"**
3. Select: **"Obtain processors from project classpath"**
4. Click `Apply` → `OK`

**Screenshot Reference:**
```
[✓] Enable annotation processing
[•] Obtain processors from project classpath
[ ] Module content root
```

---

### 3. Build the Project

**Option A: Maven Command Line (Recommended)**
```bash
mvn clean compile
```

**Option B: IntelliJ Build**
1. `Build` → `Rebuild Project`
2. Wait for completion

---

### 4. Verify Lombok is Working

**Check 1: No Compilation Errors**
```bash
mvn clean compile
```
Should complete with `[INFO] BUILD SUCCESS`

**Check 2: Verify Generated Methods**
```bash
javap target/classes/com/library/api/dto/request/CreateBookRequest.class | grep get
```

Expected output:
```
public java.lang.String getTitle();
public java.lang.String getAuthor();
public java.lang.Long getLibraryId();
...
```

**Check 3: IntelliJ Recognizes Methods**
- Open `BookController.java`
- Try typing `request.getTitle()` - IntelliJ should autocomplete
- No red underlines under Lombok-generated methods

---

### 5. Run the Application

```bash
mvn spring-boot:run
```

Expected output:
```
===========================================================
   Library Management REST API - Successfully Started
===========================================================
   Application is running on: http://localhost:8080
```

---

## 🔧 Common Lombok Issues

### Issue: "Cannot find symbol: variable log"

**Cause:** `@Slf4j` annotation not processed

**Fix:**
1. Verify Lombok plugin is installed
2. Enable annotation processing (see step 2 above)
3. Rebuild: `mvn clean compile`

---

### Issue: "Cannot find symbol: method getTitle()"

**Cause:** `@Data` annotation not processed

**Fix:**
1. Check annotation processing is enabled
2. Verify `pom.xml` has correct Lombok configuration (already fixed)
3. Run: `mvn clean compile`

---

### Issue: IntelliJ shows errors but Maven compiles fine

**Cause:** IntelliJ cache out of sync

**Fix:**
1. `File` → `Invalidate Caches...`
2. Check all options
3. Click `Invalidate and Restart`

---

## 📋 Checklist Before Running

- [ ] Lombok Plugin installed in IntelliJ
- [ ] Annotation Processing enabled
- [ ] `mvn clean compile` succeeds
- [ ] No compilation errors in console
- [ ] IntelliJ doesn't show red underlines in code

---

## 🎯 Eclipse Users

### Setup for Eclipse:

1. Download `lombok.jar`:
   ```
   https://projectlombok.org/downloads/lombok.jar
   ```

2. Run the installer:
   ```bash
   java -jar lombok.jar
   ```

3. Select your Eclipse installation
4. Click `Install/Update`
5. Restart Eclipse

6. Verify:
   - Right-click project → `Maven` → `Update Project`
   - `Project` → `Clean...`
   - `Project` → `Build Project`

---

## 🎯 VS Code Users

### Setup for VS Code:

1. Install Extension:
   - Open Extensions (`Ctrl+Shift+X`)
   - Search: "Lombok Annotations Support"
   - Install by Gabriel Basilio Brito

2. Install Java Extension Pack (if not already):
   - Search: "Extension Pack for Java"
   - Install by Microsoft

3. Reload VS Code
4. Run: `mvn clean compile`

---

## ✅ Verification Commands

### Full Build Test:
```bash
# Clean everything
mvn clean

# Compile (will process Lombok)
mvn compile

# Package (will run tests)
mvn package

# Run
mvn spring-boot:run
```

### Quick Lombok Test:
```bash
# Compile only
mvn clean compile

# Check for generated methods
javap -p target/classes/com/library/api/model/Book.class | grep -E "get|set"
```

---

## 📞 Still Having Issues?

### Enable Maven Debug Mode:
```bash
mvn clean compile -X
```

Look for:
```
[DEBUG] Annotation processor 'lombok.launch.AnnotationProcessorHider$AnnotationProcessor' matches and will be used
```

If you don't see this, Lombok isn't being picked up.

### Check Your Java Version:
```bash
java -version
```

Should show: `openjdk version "25"`

### Verify POM.xml Has Lombok Config:
```bash
grep -A 10 "maven-compiler-plugin" pom.xml
```

Should show `annotationProcessorPaths` with Lombok.

---

## 🎉 Success!

When everything works, you should:
- ✅ No compilation errors
- ✅ IntelliJ autocompletes Lombok-generated methods
- ✅ Application starts successfully
- ✅ Can test endpoints in Postman

Happy coding! 🚀
