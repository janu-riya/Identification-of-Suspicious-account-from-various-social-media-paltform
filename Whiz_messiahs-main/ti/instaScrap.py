from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait
import time
import sys

driver = webdriver.Chrome("chromedriver.exe")

try:
    url = "http://www.instagram.com/"
    driver.get(url)
    time.sleep(3)
    username = WebDriverWait(driver, 10).until(EC.element_to_be_clickable((By.CSS_SELECTOR, "input[name='username']")))
    password = WebDriverWait(driver, 10).until(EC.element_to_be_clickable((By.CSS_SELECTOR, "input[name='password']")))

    username.clear()
    username.send_keys("queengrace0528@gmail.com")
    password.clear()
    password.send_keys("jothika2000")

    log_in = WebDriverWait(driver, 2).until(EC.element_to_be_clickable((By.CSS_SELECTOR, "button[type='submit']"))).click()

    time.sleep(2)
    userName = sys.argv[1] #username to search
    driver.get(url+userName)
    time.sleep(3)


    data = {
        
    }

    n_scrolls = 2
    for j in range(0, n_scrolls):
        driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
        time.sleep(1)

    anchors = driver.find_elements_by_tag_name('a')
    anchors = [a.get_attribute('href') for a in anchors]
    anchors = [a for a in anchors if str(a).startswith("https://www.instagram.com/p/")]

    post_captions = []
    post_date = []

    for a in anchors:
        try:
            driver.get(a)
            time.sleep(1)
            caption_div = driver.find_element_by_class_name('C4VMK')
            caption_text = caption_div.find_elements_by_tag_name('span')[-1].text
            post_time = driver.find_element_by_tag_name('time').get_attribute('datetime')
            
            post_captions.append(caption_text)
            post_date.append(post_time)
            time.sleep(1)
        except:
            pass

        

    data["captions"] = post_captions
    data["dates"] = post_date

    print(data)

    
    """
    OUTPUT :
        data = {
                bio: profile_bio,
                captions: [. .. . . .],
                dates: [. .. . . .]
               }
    """
except:
    print ("Error")
finally:
    driver.quit()