using UnityEngine;
using TMPro;

public class PlayerController : MonoBehaviour
{
    [Range(5f,10f)]
    public float speed = 1f;
    Vector3 targetPosition;
    public GameObject prefab;
    public Transform firePoint;

    int num = 0;

    public GameObject[] bullets;

    public TextMeshProUGUI numBulletsText;

    void Awake() {
        Debug.Log("Awake");
    }

    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Start()
    {
        bullets = new GameObject[5];

        for(int i = 0; i < bullets.Length; i++) {
            bullets[i] = Instantiate(prefab, firePoint.position, Quaternion.identity);
            bullets[i].SetActive(false);
        }
        Debug.Log("Start");

        numBulletsText.text = "" + bullets.Length;
    }

    // Update is called once per frame
    void Update()
    {
        if(Input.GetMouseButton(0)) {
            Debug.Log("You have pressed left-click");
            targetPosition = Camera.main.ScreenToWorldPoint(Input.mousePosition);
            targetPosition.z = 0;
        }
        else {
            targetPosition = transform.position + new Vector3(Input.GetAxis("Horizontal"),Input.GetAxis("Vertical"), 0);
        }
        
        transform.position = Vector3.Lerp(transform.position, targetPosition, speed * Time.deltaTime);

        if(Input.GetMouseButtonDown(0)) {
            if(num <= bullets.Length) {
                Shoot(num);
                numBulletsText.text = (bullets.Length - num).ToString();
            }
            else {
                numBulletsText.text = "0";
                Debug.Log("No hay balas");
            }
            num++;
        }
        /* -----
            Input.mousePosition();
            Camera.main.ScreenToWorldPoint();
        */
    }

    void Shoot(int num) {
        bullets[num].transform.position = firePoint.position;
        bullets[num].transform.rotation = firePoint.rotation;
        bullets[num].SetActive(true);

        Rigidbody2D rb = bullets[num].GetComponent<Rigidbody2D>();
        if(rb != null) {
            rb.velocity = firePoint.right * 10f;
        }

        num = (num + 1) % bullets.Length;
        Destroy(bullets[num], 5f);
    }
}
