#include<iostream>
#include<string>
using namespace std;

string base10(const string &n) {

	if (n =="0" || n == "1" || n == "2" || n == "3" || n == "4" || n == "5" || n == "6" || n == "7" || n == "8" || n == "9") {
		return n;
	}
	else {
		string r = n;
		int i = 0;
		r.insert(i + 1, "0");
		i++;

		while (i + 2 < r.length()) {
			i += 2;
			r.insert(i, "0");
		}

		return r;
	}
}

bool resuelveCaso() {
	string n;

	cin >> n;

	if (!cin) return false;

	string p = base10(n);

	cout << p << "\n";

	return true;
}

int main() {
	while(resuelveCaso());
	return 0;
}