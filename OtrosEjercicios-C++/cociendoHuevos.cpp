//Jesús Cencerrado Pedrero

#include <iostream>
#include <fstream>
using namespace std;

int main()
{
	int h, c;
	int res = 0, div = 0;

	cin >> h;
	cin >> c;

	while (h != 0 && c != 0)
	{
		//if (h > c)
		//{
			div = h / c;

			if (h % c != 0)
			{
				res = 10 * (div + 1);
				cout << res << endl;
			}
			else
			{
				res = 10 * (div);
				cout << res << endl;
			}
	//	}
		/*else
		{
			cout << "10" << endl;
		}*/

		cin >> h;
		cin >> c;
	}

	return 0;
}