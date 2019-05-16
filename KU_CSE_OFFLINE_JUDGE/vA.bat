@cd "C:\Users\fardin~abir\Desktop"

@gcc A.c

@echo s;%time%

@a.exe <inpA.txt > output.txt

@echo e;%time%

@fc "C:\Users\fardin~abir\Desktop\ansA.txt" "C:\Users\fardin~abir\Desktop\output.txt" 

@echo %time%

@echo terminate
pause