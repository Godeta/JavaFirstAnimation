package animation;

//Java program to play an Audio 
import javax.sound.sampled.*;
//file using Clip Object 
import java.io.File;
import java.io.IOException;


public class SimpleAudioPlayer 
{ 

	// to store current position 
	Long currentFrame; 
	Clip clip; 
	
	// current status of clip 
	String status; 
	static String filePath;
	AudioInputStream audioInputStream; 
	private boolean playing = false;

	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	// constructor to initialize streams and clip 
	public SimpleAudioPlayer(String filePath2) 
		throws UnsupportedAudioFileException, 
		IOException, LineUnavailableException 
	{ 
		filePath = filePath2;
		// create AudioInputStream object 
		audioInputStream = 
				AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile()); 
		
		// create clip reference 
		clip = AudioSystem.getClip(); 
		
		// open audioInputStream to the clip 
		clip.open(audioInputStream); 
		
		clip.loop(Clip.LOOP_CONTINUOUSLY); 
		playing = true;
	} 

	/*public static void main(String[] args) 
	{ 
		try
		{ 
			filePath = "Sound/party_started.wav"; 
			SimpleAudioPlayer audioPlayer = 
							new SimpleAudioPlayer(); 
			
			audioPlayer.play(); 
			Scanner sc = new Scanner(System.in); 
			
			while (true) 
			{ 
				System.out.println("1. pause"); 
				System.out.println("2. resume"); 
				System.out.println("3. restart"); 
				System.out.println("4. stop"); 
				System.out.println("5. Jump to specific time"); 
				int c = sc.nextInt(); 
				audioPlayer.gotoChoice(c); 
				if (c == 4) 
				break; 
			} 
			sc.close(); 
		} 
		
		catch (Exception ex) 
		{ 
			System.out.println("Error with playing sound."); 
			ex.printStackTrace(); 
		
		} 
	} 
	*/
	// Work as the user enters his choice 
	
	/*private void gotoChoice(int c) 
			throws IOException, LineUnavailableException, UnsupportedAudioFileException 
	{ 
		switch (c) 
		{ 
			case 1: 
				pause(); 
				break; 
			case 2: 
				resumeAudio(); 
				break; 
			case 3: 
				restart(); 
				break; 
			case 4: 
				stop(); 
				break; 
			case 5: 
				System.out.println("Enter time (" + 0 + 
				", " + clip.getMicrosecondLength() + ")"); 
				Scanner sc = new Scanner(System.in); 
				long c1 = sc.nextLong(); 
				jump(c1); 
				break; 
	
		} 
	
	} */
	
	// Method to play the audio 
	public void play() 
	{ 
		//start the clip 
		clip.start(); 
		
		status = "play"; 
		playing = true;
	} 
	
	// Method to pause the audio 
	public void pause() 
	{ 
		if (status.equals("paused")) 
		{ 
			System.out.println("audio is already paused"); 
			return; 
		} 
		this.currentFrame = 
		this.clip.getMicrosecondPosition(); 
		clip.stop(); 
		status = "paused"; 
		playing = false;
	} 
	
	// Method to resume the audio 
	public void resumeAudio() throws UnsupportedAudioFileException, 
								IOException, LineUnavailableException 
	{ 
		if (status.equals("play")) 
		{ 
			System.out.println("Audio is already "+ 
			"being played"); 
			return; 
		} 
		clip.close(); 
		resetAudioStream(); 
		clip.setMicrosecondPosition(currentFrame); 
		this.play(); 
	} 
	
	// Method to restart the audio 
	public void restart() throws IOException, LineUnavailableException, 
											UnsupportedAudioFileException 
	{ 
		clip.stop(); 
		clip.close(); 
		resetAudioStream(); 
		currentFrame = 0L; 
		clip.setMicrosecondPosition(0); 
		this.play(); 
	} 
	
	// Method to stop the audio 
	public void stop() throws UnsupportedAudioFileException, 
	IOException, LineUnavailableException 
	{ 
		playing = false;
		currentFrame = 0L; 
		clip.stop(); 
		 
	} 
	
	public void close() {
		playing = false;
		clip.close();
	}
	
	// Method to jump over a specific part 
	public void jump(long c) throws UnsupportedAudioFileException, IOException, 
														LineUnavailableException 
	{ 
		if (c > 0 && c < clip.getMicrosecondLength()) 
		{ 
			
			clip.stop(); 
			clip.close(); 
			resetAudioStream(); 
			currentFrame = c; 
			clip.setMicrosecondPosition(c); 
			this.play(); 
		} 
	} 
	
	// Method to reset audio stream 
	public void resetAudioStream() throws UnsupportedAudioFileException, IOException, 
											LineUnavailableException 
	{ 
		audioInputStream = AudioSystem.getAudioInputStream( 
		new File(filePath).getAbsoluteFile()); 
		clip.open(audioInputStream); 
		clip.loop(Clip.LOOP_CONTINUOUSLY); 
	} 

} 
