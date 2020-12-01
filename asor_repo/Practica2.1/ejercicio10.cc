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

	//datos usuario
	struct passwd *pass = getpwuid(uid);
	char *name = pass->pw_name;
	char *dir = pass->pw_dir;
	char *info = pass->pw_gecos;

	cout << "Name: " << name << '\n';
	cout << "Home: " << dir << '\n';
	cout << "Information: " << info << '\n';

	return 1;
}