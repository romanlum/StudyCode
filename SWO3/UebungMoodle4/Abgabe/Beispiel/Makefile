CC = gcc
CFLAGS = -g -Og -ansi -pedantic -Wall -Wextra

LD = gcc
LDFLAGS = -lm

PROGRAM = find

CFILES = $(wildcard *.c)
OFILES = $(CFILES:.c=.o)

%.o: %.c
	$(CC) $(CFLAGS) -c $<
	

$(PROGRAM): $(OFILES)
	# Linker decides if libraries are used on ofiles 
	$(LD) -o $@ $(OFILES) $(LDFLAGS)
	
clean:
	rm -f $(OFILES) $(PROGRAM)
	
depend:
	@cat < /dev/null > makedep
	@for i in $(CFILES); do \
		($(CC) -MM $$i >> makedep); done
	@echo "/^# BEGIN DEPENDENCIES\$$/+1,\$$d" > edcmds
	@echo "r makedep" >> edcmds
	@echo "wq" >> edcmds 	
	@ed -v - Makefile < edcmds
	@rm edcmds makedep
	@echo "# LINE REQUIRED FOR MAKEDEPEND" >> Makefile
	
# BEGIN DEPENDENCIES
find.o: find.c
# LINE REQUIRED FOR MAKEDEPEND
