namespace SteganographyEngine
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null)) {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form1));
            this.label1 = new System.Windows.Forms.Label();
            this.fileBox = new System.Windows.Forms.TextBox();
            this.fileButton = new System.Windows.Forms.Button();
            this.pictureBox = new System.Windows.Forms.PictureBox();
            this.textBox = new System.Windows.Forms.TextBox();
            this.encryptButton = new System.Windows.Forms.Button();
            this.decryptButton = new System.Windows.Forms.Button();
            this.charCount = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox)).BeginInit();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(15, 6);
            this.label1.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(103, 13);
            this.label1.TabIndex = 0;
            this.label1.Text = "Select an Image file:";
            // 
            // fileBox
            // 
            this.fileBox.Location = new System.Drawing.Point(17, 21);
            this.fileBox.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.fileBox.Name = "fileBox";
            this.fileBox.Size = new System.Drawing.Size(204, 20);
            this.fileBox.TabIndex = 1;
            // 
            // fileButton
            // 
            this.fileButton.Location = new System.Drawing.Point(230, 21);
            this.fileButton.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.fileButton.MinimumSize = new System.Drawing.Size(40, 20);
            this.fileButton.Name = "fileButton";
            this.fileButton.Size = new System.Drawing.Size(40, 20);
            this.fileButton.TabIndex = 2;
            this.fileButton.Text = "...";
            this.fileButton.UseVisualStyleBackColor = true;
            this.fileButton.Click += new System.EventHandler(this.fileButton_Click);
            // 
            // pictureBox
            // 
            this.pictureBox.BackColor = System.Drawing.SystemColors.ButtonFace;
            this.pictureBox.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.pictureBox.Location = new System.Drawing.Point(17, 48);
            this.pictureBox.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.pictureBox.Name = "pictureBox";
            this.pictureBox.Size = new System.Drawing.Size(253, 207);
            this.pictureBox.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.pictureBox.TabIndex = 3;
            this.pictureBox.TabStop = false;
            // 
            // textBox
            // 
            this.textBox.Enabled = false;
            this.textBox.Location = new System.Drawing.Point(17, 264);
            this.textBox.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.textBox.Multiline = true;
            this.textBox.Name = "textBox";
            this.textBox.Size = new System.Drawing.Size(254, 63);
            this.textBox.TabIndex = 4;
            this.textBox.TextChanged += new System.EventHandler(this.textBox_TextChanged);
            // 
            // encryptButton
            // 
            this.encryptButton.Enabled = false;
            this.encryptButton.Location = new System.Drawing.Point(16, 350);
            this.encryptButton.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.encryptButton.Name = "encryptButton";
            this.encryptButton.Size = new System.Drawing.Size(120, 36);
            this.encryptButton.TabIndex = 5;
            this.encryptButton.Text = "Encrypt";
            this.encryptButton.UseVisualStyleBackColor = true;
            this.encryptButton.Click += new System.EventHandler(this.encryptButton_Click);
            // 
            // decryptButton
            // 
            this.decryptButton.Enabled = false;
            this.decryptButton.Location = new System.Drawing.Point(151, 350);
            this.decryptButton.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.decryptButton.Name = "decryptButton";
            this.decryptButton.Size = new System.Drawing.Size(120, 36);
            this.decryptButton.TabIndex = 6;
            this.decryptButton.Text = "Decrypt";
            this.decryptButton.UseVisualStyleBackColor = true;
            this.decryptButton.Click += new System.EventHandler(this.decryptButton_Click);
            // 
            // charCount
            // 
            this.charCount.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.charCount.BackColor = System.Drawing.SystemColors.Menu;
            this.charCount.Enabled = false;
            this.charCount.Location = new System.Drawing.Point(18, 328);
            this.charCount.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.charCount.Name = "charCount";
            this.charCount.RightToLeft = System.Windows.Forms.RightToLeft.Yes;
            this.charCount.Size = new System.Drawing.Size(252, 14);
            this.charCount.TabIndex = 7;
            this.charCount.Text = "0/0";
            this.charCount.TextAlign = System.Drawing.ContentAlignment.BottomLeft;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(294, 401);
            this.Controls.Add(this.charCount);
            this.Controls.Add(this.decryptButton);
            this.Controls.Add(this.encryptButton);
            this.Controls.Add(this.textBox);
            this.Controls.Add(this.pictureBox);
            this.Controls.Add(this.fileButton);
            this.Controls.Add(this.fileBox);
            this.Controls.Add(this.label1);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Margin = new System.Windows.Forms.Padding(2, 2, 2, 2);
            this.MaximizeBox = false;
            this.MaximumSize = new System.Drawing.Size(310, 477);
            this.MinimumSize = new System.Drawing.Size(310, 396);
            this.Name = "Form1";
            this.Padding = new System.Windows.Forms.Padding(4, 0, 0, 0);
            this.Text = " Steganography Engine";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox fileBox;
        private System.Windows.Forms.Button fileButton;
        private System.Windows.Forms.PictureBox pictureBox;
        private System.Windows.Forms.TextBox textBox;
        private System.Windows.Forms.Button encryptButton;
        private System.Windows.Forms.Button decryptButton;
        private System.Windows.Forms.Label charCount;


    }
}

