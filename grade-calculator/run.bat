@echo off
echo Compilando...
if not exist out mkdir out
javac -d out src/main/java/edu/utec/gradecalculator/exceptions/*.java src/main/java/edu/utec/gradecalculator/*.java
if %errorlevel% neq 0 (
    echo Error de compilacion.
    pause
    exit /b %errorlevel%
)

echo Ejecutando CS-GradeCalculator...
java -cp out edu.utec.gradecalculator.App
pause
