/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.sound.midi.*;
/**
 *
 * @author e_ver
 */
public class MIDI{
    public static void main(String[]args){
        MIDI mini = new MIDI();
        if (args.length <2){
            System.out.println("instr and notes");
        }else{
            int instrument = 102;
            int note = 30;
            mini.play(instrument, note);
        }
    }
    
    public void play(int instrument, int note){
        try{
            Sequencer player = MidiSystem.getSequencer();
            player.open();
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();
            
            MidiEvent event = null;
            ShortMessage first = new ShortMessage();
            first.setMessage(192,1,instrument,0);
            MidiEvent changeInstrument = new MidiEvent(first,1);
            track.add(changeInstrument);
            
            ShortMessage a = new ShortMessage();
            a.setMessage(144,1,note,100);
            MidiEvent noteOn = new MidiEvent(a,1);
            track.add(noteOn);
            
            player.setSequence(seq);
            player.start();
        }catch(Exception ex){}
    }
}