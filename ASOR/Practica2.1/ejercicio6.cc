#include <iostream>
#include <unistd.h>
#include <limits.h>
using namespace std;

int main() {
	cout << sysconf(_SC_ARG_MAX) << "\n";
	cout << sysconf(_SC_CHILD_MAX) << "\n";
	cout << sysconf(_SC_OPEN_MAX) << "\n";

	return 1;
}

