### 🔹 Step 1: Check Bucket Public Access Settings

1. Go to **AWS Console → S3**.
2. Select your bucket (**my-public-bucket**).
3. Go to **Permissions tab → Block public access (bucket settings)**.
4. Click **Edit**.
5. Uncheck **Block all public access** (or at least uncheck *Block public access to objects*).
6. Save changes. ✅



### 🔹 Step 2: Add a Bucket Policy (for public read)

1. Still in **Permissions tab → Bucket policy**.
2. Add this JSON (replace with your bucket name):

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Sid": "PublicReadGetObject",
      "Effect": "Allow",
      "Principal": "*",
      "Action": "s3:GetObject",
      "Resource": "<bucket-arn>/*"
    }
  ]
}
```

3. Save. ✅



### 🔹 Step 3: Set Object Public

1. Go to **Objects tab** → select your file (`file.txt`).
2. Click **Actions → Make public using ACL**.
3. Confirm. ✅



### 🔹 Step 4: Verify Access

Now your file is public:

```
https://my-public-bucket.s3.ap-south-1.amazonaws.com/file.txt
```



