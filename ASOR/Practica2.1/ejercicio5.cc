#include <iostream>
#include <sys/utsname.h>
using namespace std;

int main() {
	struct utsname unamedata;
	uname(&unamedata);

	cout << unamedata.sysname << "\n";
	cout << unamedata.nodename << "\n";
	cout << unamedata.release << "\n";
	cout << unamedata.version << "\n";
	cout << unamedata.machine << "\n";

	return 1;
}

