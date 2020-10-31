#include<iostream>
#include<vector>
#include<string>
using namespace std;

const int MAX = 250000;
int vec[MAX];

//{Pre: 0 <= n <= long(v) ^ v.size() > 0}
int numSeg(int v[], const int tam, const int k) { //return ret
	int ret = 0;
	int a = 0, b = 0;
	int sumaVentana = 0;

	while (b < tam || sumaVentana > k) {
		if (sumaVentana < k) {
			sumaVentana += v[b];
			b++;
		}
		else {
			sumaVentana -= v[a];
			a++;
		}

		if (sumaVentana == k) ret++;
	}

	return ret;
}
/*{Post: 
	ret = # i, j : 0 <= i <= j < n : suma(v, i, j) = k}
  }

  Donde suma(v, a, b) = sum i : a <= i <= b : v[i]
*/
void resuelveCaso() {
	int num;
	string line;
	//vector<int> v;

	cin >> num >> line;

	for (int i = 0; i < line.size(); i++) {
		//v.push_back(line.at(i) - '0');
		vec[i] = line[i] - '0';
	}

	int p = numSeg(vec, line.size(), num);

	cout << p << "\n";
}

int main() {
	int casos;

	cin >> casos;

	for (int i = 0; i < casos; i++) {
		resuelveCaso();
	}

	return 0;
}