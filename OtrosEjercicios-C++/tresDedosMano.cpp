#include <iostream>
using namespace std;

int baseSeis(int &q, int &num, int &p, int &i)
{
	int k;

	if (q == 0) return 0;
	else if (q > 0)
	{
		i *= p;
		num += ((q % 6) * i)/10;
		q /= 6;

		k = baseSeis(q, num, p, i);
	}
	return num;
}

int main()
{
	int casos, p, q, po = 0, l = 10, m = 1;

	cin >> casos;

	for (int i = 0; i < casos; i++)
	{
		cin >> q;

		p = baseSeis(q, po, l, m);

		cout << p << endl;

		po = 0;
		l = 10;
		m = 1;
	}

	//system("pause");
	return 0;
}