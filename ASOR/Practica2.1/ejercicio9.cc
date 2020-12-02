#include <iostream>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/utsname.h>
using namespace std;

int main() {

	uid_t uid = getuid();
	cout << "Real: " << uid << '\n';
	cout << "Efectivo: " << geteuid() << '\n';

	return 1;
}