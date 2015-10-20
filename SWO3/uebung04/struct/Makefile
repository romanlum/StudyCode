CC = gcc
CFLAGS = -g -Og -ansi -pedantic -Wall -Wextra

LD = gcc
LDFLAGS = -lm

PROGRAM = list

CFILES = $(wildcard *.c)
OFILES = $(CFILES:.c=.o)

%.o: %.c
	$(CC) -DTEST $(CFLAGS) -c $<
	

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
list_adt.o: list_adt.c
list_test.o: list_test.c list_adt.h
# LINE REQUIRED FOR MAKEDEPEND
