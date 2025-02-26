using UnityEngine;
using System.Collections.Generic;
using TMPro;

public class PlayerController : MonoBehaviour
{
    [Range(5f,10f)]
    public float speed = 1f;
    Vector3 targetPosition;
    public GameObject prefabBullet;
    private int poolSize = 5;
    public Transform firePoint;
    private int num = 0;
    public TextMeshProUGUI numBulletsText;

    private Queue<GameObject> pool = new Queue<GameObject>();

    void Awake() {
        Debug.Log("Awake");
    }

    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Start()
    {
        for(int i = 0; i < poolSize; i++) {
            GameObject bullet = Instantiate(bulletPrefab);
            bullets[i].SetActive(false);
            pool.Enqueue(bullet)
        }
        Debug.Log("Start");
        
        numBulletsText.text = "" + poolSize;
    }

    // Update is called once per frame
    void Update()
    {
        targetPosition = transform.position + new Vector3(Input.GetAxis("Horizontal"),Input.GetAxis("Vertical"), 0);

        /*
        if(Input.GetMouseButton(0)) {
            targetPosition = Camera.main.ScreenToWorldPoint(Input.mousePosition);
            targetPosition.z = 0;
        }
        else {
            targetPosition = transform.position + new Vector3(Input.GetAxis("Horizontal"),Input.GetAxis("Vertical"), 0);
        }
        */
        
        transform.position = Vector3.Lerp(transform.position, targetPosition, speed * Time.deltaTime);

        if(Input.GetMouseButtonDown(1)) {
            if(num < bullets.Length) {
                Shoot(num);
                num++;
                numBulletsText.text = "" + (bullets.Length - num);
            }
            else {
                Debug.Log("No hay balas");
            }
        }
    }

    void Shoot(int num) {
        bullets[num].transform.SetPositionAndRotation(firePoint.position, firePoint.rotation);
        bullets[num].SetActive(true);

        Rigidbody2D rb = bullets[num].GetComponent<Rigidbody2D>();
        if(rb != null) {
            rb.linearVelocity = firePoint.right * 10f;
        }
        Destroy(bullets[num], 5f);
    }

    public GameObject GetBullet()
    {
        if (pool.Count > 0)
        {
            GameObject bullet = pool.Dequeue();
            bullet.SetActive(true);
            return bullet;
        }
        else
        {
            GameObject bullet = Instantiate(bulletPrefab);
            return bullet;
        }
    }

    public void ReturnBullet(GameObject bullet)
    {
        bullet.SetActive(false);
        pool.Enqueue(bullet);
    }

    public void ReloadBullets()
    {
        num = 0;
        for (int i = 0; i < bullets.Length; i++)
        {
            bullets[i] = Instantiate(prefabBullet, firePoint.position, Quaternion.identity);
            bullets[i].SetActive(false);
        }
        numBulletsText.text = bullets.Length.ToString();
    }
}
