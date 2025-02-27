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
    public TextMeshProUGUI numBulletsText;

    private Queue<GameObject> pool = new Queue<GameObject>();

    void Awake() {
        Debug.Log("Awake");
    }

    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Start()
    {
        for(int i = 0; i < poolSize; i++) {
            GameObject bullet = Instantiate(prefabBullet);
            bullet.SetActive(false);
            pool.Enqueue(bullet);
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
            Shoot();
        }
    }

    void Shoot() {
        GameObject bullet = GetBullet();
        if(bullet != null) {
            bullet.transform.SetPositionAndRotation(firePoint.position, firePoint.rotation);

            Rigidbody2D rb = bullet.GetComponent<Rigidbody2D>();
            if(rb != null) {
                rb.linearVelocity = firePoint.right * 10f;
            }
        }
        else Debug.Log("No hay balas");
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
            return null;
        }
    }

    public void ReturnBullet(GameObject bullet)
    {
        bullet.SetActive(false);
        pool.Enqueue(bullet);
    }

    public void ReloadBullets()
    {
        GameObject[] activeBullets = GameObject.FindGameObjectsWithTag("Bullet");

        foreach (GameObject bullet in activeBullets)
        {
            ReturnBullet(bullet);
        }
    }
}
